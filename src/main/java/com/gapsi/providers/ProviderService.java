package com.gapsi.providers;

import com.gapsi.providers.beans.BeanListProvider;
import com.gapsi.providers.beans.BeanProvider;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ProviderService {

    private final AppFeature appFeature;

    public ProviderService(AppFeature appFeature) {
        this.appFeature = appFeature;
    }

    /**
     * @return Providers list
     */
    public List<BeanProvider> getProviders(){
        Gson gson = new Gson();
        return gson.fromJson(getJsonInfo(), BeanListProvider.class).getProviders();
    }

    /**
     * Get paged results for providers
     *
     * @param page
     * @param size
     * @return
     */
    public String getProviders(Integer page, Integer size){
        Gson gson = new Gson();
        BeanListProvider listProvider = gson.fromJson(getJsonInfo(), BeanListProvider.class);

        List<BeanProvider> providers = listProvider.getProviders()
                .stream()
                .skip((long) page * size)
                .limit(size)
                .toList();

        int totalPages = (int) Math.ceil((double) listProvider.getProviders().size() / size);

        Map<String, Object> response = new HashMap<>();
        response.put("providers", providers);
        response.put("totalPages", totalPages);
        response.put("currentPage", page);
        response.put("totalElements", listProvider.getProviders().size());

        return gson.toJson(response);
    }

    /**
     * Create a provider in db.json
     *
     * @param beanProvider using @{@link BeanProvider} properties
     * @return
     */
    public ResponseEntity createProvider(BeanProvider beanProvider){
        Gson gson = new Gson();
        BeanListProvider listProvider = gson.fromJson(getJsonInfo(), BeanListProvider.class);

        List<BeanProvider> providers = listProvider.getProviders()
                .stream()
                .filter(c -> c.getProviderName().equalsIgnoreCase(beanProvider.getProviderName()))
                .toList();

        if(providers.isEmpty()){
            UUID uuid = UUID.randomUUID();
            beanProvider.setProviderId(uuid.toString());
            listProvider.getProviders().add(beanProvider);
            saveJsonInfo(gson.toJson(listProvider));
            try {
                return ResponseEntity.created(new URI("/providers/" + beanProvider.getProviderId())).body(beanProvider);
            } catch (URISyntaxException e) {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error creating provider " + beanProvider.getProviderName());
            }
        }else{
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Provider " + beanProvider.getProviderName() + " already exists");
        }
    }


    /**
     * Delete a provider using providerId(UUID)
     *
     * @param providerId
     * @return @{@link ResponseEntity}
     */
    public ResponseEntity<Object> deleteProvider(String providerId){
        Gson gson = new Gson();
        System.out.println("LLegue");
        BeanListProvider listProvider = gson.fromJson(getJsonInfo(), BeanListProvider.class);
        List<BeanProvider> providers = listProvider.getProviders();
        providers.removeIf( o -> o.getProviderId().equals(providerId));
        listProvider.setProviders(providers);
        saveJsonInfo(gson.toJson(listProvider));
        return ResponseEntity.ok().build();
    }

    /**
     * @return Save the String information to db.json
     */
    private void saveJsonInfo(String jsonInfo){
        try {
            Path dkf= Paths.get(getClass().getClassLoader().getResource(appFeature.getAppDbLocation()).getFile());
            Files.writeString( dkf, "", StandardCharsets.UTF_8);
            Files.writeString( dkf, jsonInfo, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * @return The information from db.json in String
     */
    private String getJsonInfo(){
        try{
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(appFeature.getAppDbLocation());
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String read;
            while ((read=br.readLine()) != null) {
                sb.append(read);
            }
            br.close();
            return sb.toString();
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

}

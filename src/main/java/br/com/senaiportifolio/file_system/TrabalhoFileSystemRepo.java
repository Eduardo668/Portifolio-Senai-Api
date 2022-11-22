package br.com.senaiportifolio.file_system;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Repository;

@Repository
@Deprecated
public class TrabalhoFileSystemRepo {
    
    public Path currentDir = Paths.get("").toAbsolutePath();
    public String fileSystemDir = currentDir.normalize().toString();

    public String trabalhoFileSystemDir = fileSystemDir + "/TrabalhoImages";

    public File pathAsFile = new File(trabalhoFileSystemDir);

    public String save(byte[] content, String imageName) throws Exception{

        if(!Files.exists(Paths.get(trabalhoFileSystemDir))){
            pathAsFile.mkdir();
        }   

        Path newImage = Paths.get(trabalhoFileSystemDir, new Date().getTime() + "-" + imageName);
        Files.createDirectories(newImage.getParent());

        Files.write(newImage, content);

        return newImage.toAbsolutePath().toString();
    }

    public FileSystemResource findInTrabalhoImages(String location){
        try{
            return new FileSystemResource(Paths.get(location));
        }
        catch(Exception e){
            throw new RuntimeException("Erro ao retornar a imagem do file system", e);
        }
    }


}

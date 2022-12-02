package br.com.senaiportifolio.routes;

import java.util.List;


import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import br.com.senaiportifolio.models.Trabalho;
import br.com.senaiportifolio.services.TrabalhoServiceImpl;

@RestController
@RequestMapping("api")
@CrossOrigin(value = "*")
public class TrabalhoController {

    private final TrabalhoServiceImpl trabalhoService;
    
    public TrabalhoController(TrabalhoServiceImpl trabalhoService) {
        this.trabalhoService = trabalhoService;
    }

    @PostMapping("/createTrabalho")
    public ResponseEntity<Trabalho> createTrabalho(@RequestBody Trabalho newTrabalho){
        try{
            return ResponseEntity.ok(trabalhoService.createTrabalho(newTrabalho));
        }
        catch(Exception e){
            throw new RuntimeException("Erro ao realizar a requisição de criação do trabalho");
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Trabalho>> findAllTrabalhos(){
        return ResponseEntity.ok(trabalhoService.findAllTrabalhos());
    }

    @GetMapping("/findTrabalhosBySubject/subject={subject}")
    public ResponseEntity<List<Trabalho>> findAllTrabalhoBySubject(@PathVariable("subject") String subject){
        return ResponseEntity.ok(trabalhoService.findAllTrabalhosBySubject(subject));

    }

    @DeleteMapping("/deleteTrabalho/trabalho_id={trabalho_id}")
    public ResponseEntity<String> deleteTrabalho(@PathVariable("trabalho_id") Long trabalho_id){
        try{
            trabalhoService.deleteTrabalho(trabalho_id);
            return ResponseEntity.ok("Trabalho Deletado com sucesso!!");
        }
        catch(Exception e){
            throw new RuntimeException("Erro ao realizar a requisição para deletar o trabalho");
        }
    
    }

    @PutMapping("/editTrabalho/trabalho_id={trabalho_id}")
    public ResponseEntity<Trabalho> editTrabalho(@PathVariable("trabalho_id") Long trabalho_id,
        @RequestBody Trabalho editedTrabalho){
        try{
            return ResponseEntity.ok(trabalhoService.editTrabalho(trabalho_id, editedTrabalho));
        }
        catch(Exception e){
            throw new RuntimeException("Erro ao realizar a requisição para deletar o trabalho");
        }
    

    }

    @Deprecated
    @PutMapping("/saveTrabalhoImage/trabalho_id={trabalho_id}")
    public ResponseEntity<Trabalho> saveTrabalhoImage(@RequestParam MultipartFile imageFile,
                 @PathVariable Long trabalho_id){
            
        try{
            return ResponseEntity.ok(trabalhoService.saveTrabalhoImage(imageFile.getBytes(), imageFile.getOriginalFilename(), trabalho_id));
        }
        catch(Exception e){
            throw new RuntimeException("Erro na requisição para salvar a imagem do trabalho");
        }
    
    }

    @Deprecated
    @GetMapping(value = "/findTrabalhoImage/trabalho_id={trabalho_id}", produces = org.springframework.http.MediaType.IMAGE_PNG_VALUE )
    public ResponseEntity<FileSystemResource> findTrabalhoImage(@PathVariable Long trabalho_id){
        return ResponseEntity.ok(trabalhoService.findTrabalhoImage(trabalho_id));
    }




}

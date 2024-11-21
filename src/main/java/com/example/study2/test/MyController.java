package com.example.study2.test;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/test")
public class MyController {

    MyRepository myRepository;

    MyController(MyRepository myRepository){
        this.myRepository = myRepository;
    }

    @PostMapping("/test")
    public void createPSW(@RequestBody PSWDTO pswdto){
        PSW psw = new PSW(pswdto.getId(), pswdto.getName());

        myRepository.save(psw);
    }

    @GetMapping("/test")
    public List<PSW> findAllPSW(){
        return myRepository.findAll();
    }

    @GetMapping("/test/{id}")
    public PSW findByIdPSW(@PathVariable Long id){
        return myRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @PutMapping("/test/{id}")
    public void updatePSW(@PathVariable Long id,
                          @RequestBody PSWDTO pswdto
    ){
        PSW findPSW = myRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        findPSW.updateByDTO(pswdto);

        myRepository.save(findPSW);
    }

    @DeleteMapping("/test/{id}")
    public void deletePSW(@PathVariable Long id){
        PSW findPSW = myRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        myRepository.delete(findPSW);
    }
}

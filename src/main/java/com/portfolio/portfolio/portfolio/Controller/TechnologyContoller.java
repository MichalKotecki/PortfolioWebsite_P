package com.portfolio.portfolio.portfolio.Controller;

import com.portfolio.portfolio.portfolio.Error.ResourceNotFoundException;
import com.portfolio.portfolio.portfolio.Models.Technology;
import com.portfolio.portfolio.portfolio.Repository.TechnologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class TechnologyContoller {

    @Autowired
    private TechnologyRepository technologyRepository;

    @GetMapping("/technologies")
    public List<Technology> getAllTechnologies(){
        return  technologyRepository.findAll();
    }

    @GetMapping("/technologies/{id}")
    public ResponseEntity<Technology> getTechnologyById(
            @PathVariable(value = "id") Long technologyID) throws ResourceNotFoundException {
        Technology technology = technologyRepository.findById(technologyID)
                .orElseThrow(() -> new ResourceNotFoundException("Technology not found on :: "+ technologyID));
        return ResponseEntity.ok().body(technology);
    }

    @PostMapping("/technologies")
    public Technology createTechnology(@Valid @RequestBody Technology technnology) {
        return technologyRepository.save(technnology);
    }

    @PutMapping("/technologies/{id}")
    public ResponseEntity<Technology> updateTechnology(
            @PathVariable(value = "id") Long technologyID,
            @Valid @RequestBody Technology technologyDetails) throws ResourceNotFoundException {
        Technology technology = technologyRepository.findById(technologyID)
                .orElseThrow(() -> new ResourceNotFoundException("Technology not found on :: "+ technologyID));

        technology.setTechnologyName(technologyDetails.getTechnologyName());
        final Technology updatedTechnology = technologyRepository.save(technology);
        return ResponseEntity.ok(updatedTechnology);
    }

    @DeleteMapping("/technology/{id}")
    public Map<String, Boolean> deleteTechnology(
            @PathVariable(value = "id") Long technologyID) throws Exception {
        Technology technology = technologyRepository.findById(technologyID)
                .orElseThrow(() -> new ResourceNotFoundException("Technology not found on :: "+ technologyID));

        technologyRepository.delete(technology);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}

package com.workintech.s17d2.rest;

import com.workintech.s17d2.enums.Experience;
import com.workintech.s17d2.model.Developer;
import com.workintech.s17d2.model.JuniorDeveloper;
import com.workintech.s17d2.model.MidDeveloper;
import com.workintech.s17d2.model.SeniorDeveloper;
import com.workintech.s17d2.tax.DeveloperTax;
import com.workintech.s17d2.tax.Taxable;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/developers")
public class DeveloperController {
    public Map<Integer, Developer> developers;
    private final Taxable developerTax;

    @PostConstruct
    public void init(){
        developers = new HashMap<>();
    }

    public DeveloperController(Taxable developerTax){
        this.developerTax = developerTax;
    }

    @GetMapping
    public List<Developer> getDevelopers(){
        return developers.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Developer getDeveloper(@PathVariable Integer id){
        return developers.get(id);
    }

    @PostMapping
    public ResponseEntity<Developer> createDeveloper(@RequestBody Developer developer){
        Double salary = developer.getSalary();

        switch (developer.getExperience()){
            case JUNIOR :
                salary -= salary * developerTax.getSimpleTaxRate() / 100;
                developer = new JuniorDeveloper(developer.getId(), developer.getName(), salary);
                break;
            case MID:
                salary -= salary * developerTax.getMiddleTaxRate() / 100;
                developer = new MidDeveloper(developer.getId(), developer.getName(), salary);
                break;
            case SENIOR :
                salary -= salary * developerTax.getUpperTaxRate() / 100;
                developer = new SeniorDeveloper(developer.getId(), developer.getName(), salary);
                break;
        }

        developers.put(developer.getId(), developer);
        return new ResponseEntity(developer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Developer updateDeveloper(@PathVariable Integer id, @RequestBody Developer developer) {
        developers.put(id, developer);
        return developers.get(id);
    }

    @DeleteMapping("/{id}")
    public String deleteDeveloper(@PathVariable Integer id) {
        developers.remove(id);
        return "Deleted";
    }
}

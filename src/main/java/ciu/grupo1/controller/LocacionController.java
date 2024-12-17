package ciu.grupo1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import ciu.grupo1.dto.LocacionDto;
import ciu.grupo1.service.LocacionService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/locaciones")
public class LocacionController {

    @Autowired
    private LocacionService locacionService;
    
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<LocacionDto> listarTodas() {
        return locacionService.listarTodas();
    }

    
   
    @GetMapping("/buscar")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> buscarPorNombre(@RequestParam String nombre) {
         List<LocacionDto> resultados = locacionService.buscarPorNombre(nombre);
         if(resultados.isEmpty()) {
             return ResponseEntity.status(HttpStatus.NOT_FOUND)
                     .body("La locacion q buscas no existe!");        	 
         } 
         return ResponseEntity.ok(resultados);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> buscarPorId(@PathVariable UUID id) {
        Optional<LocacionDto> resultados = locacionService.buscarPorId(id);
        if(resultados == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("La locacion q buscas no existe!");        	 
        } 
        return ResponseEntity.ok(resultados);
    
    }
    
    


   
    @GetMapping("/filtrar")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> filtrarPorCapacidad(@RequestParam Integer capacidadMaxima) {
        List<LocacionDto> resultados = locacionService.filtrarPorCapacidad(capacidadMaxima);
        if (resultados.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron locaciones con capacidad maxima de " + capacidadMaxima);
        }
        return ResponseEntity.ok(resultados);
    }


    
    @PostMapping("/crear")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public LocacionDto crear(@RequestBody LocacionDto locacionDto) {
        return locacionService.guardar(locacionDto);
    }

    @PutMapping("/{id}") /*<-- capas q por nombre quedaria mejor*/
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public LocacionDto actualizar(@PathVariable UUID id, @RequestBody LocacionDto locacionDto) {
        locacionDto.setId(id);
        return locacionService.guardar(locacionDto);
    }

    
    @DeleteMapping("/{id}")/*<-- capas q por nombre quedaria mejor*/
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void eliminar(@PathVariable UUID id) {
        locacionService.eliminar(id);
    }
}


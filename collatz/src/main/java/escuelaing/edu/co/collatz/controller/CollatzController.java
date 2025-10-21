package escuelaing.edu.co.collatz.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CollatzController {

    @GetMapping("/collatzsequence")
    public String greeting(@RequestParam String value) {
        return "{\"operation\":\"collatzsequence\", \"input\": " + value + ", \"output\": " + collatzsequence(value) + "}";
    }

    public String collatzsequence(String value){
        Integer valor = Integer.parseInt(value);
        String sequence = value + " -> ";
        while (valor != 1){
            if(valor % 2 == 0){
                valor = valor / 2;
                if(valor == 1){
                    sequence += valor;
                }else{
                    sequence += valor + " -> ";
                }
            } else {
                valor = 3 * valor + 1;
                if(valor == 1){
                    sequence += valor;
                }else{
                    sequence += valor + " -> ";
                }
            }
        }
        return sequence;
    }
}
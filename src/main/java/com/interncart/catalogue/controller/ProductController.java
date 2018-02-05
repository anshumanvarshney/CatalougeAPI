package com.interncart.catalogue.controller;

import com.interncart.catalogue.entity.Product;
import com.interncart.catalogue.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

@RestController
@RequestMapping("/catalogue")
public class ProductController {

    @Autowired
    ProductServices productServices;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody Product product){
        productServices.add(product);
        return new ResponseEntity<>("added " + product.getpName(), HttpStatus.OK);
    }

    @RequestMapping("/delete/{productId}")
    public ResponseEntity<?> delete(@PathVariable("productId") String productId){
        productServices.remove(productId);
        return new ResponseEntity<>("Deleted!", HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ResponseEntity<?> updateQuantity(@RequestParam("d") Integer delta,@RequestParam("pid") String productId){
        productServices.updateQuantity(productId, delta);
        return new ResponseEntity<>("Updated!", HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateBulk(@RequestBody HashMap<String, Integer> updateMap){
        //increase | decrease quantity of each pid by delta
        productServices.updateBulk(updateMap);
        return new ResponseEntity<>("Everything went well", HttpStatus.OK);
    }
}

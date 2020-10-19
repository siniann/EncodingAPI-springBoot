package com.bitmovin.trial.encodingapi.rest;

import com.bitmovin.trial.encodingapi.exceptions.CustomNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * User: sini_ann
 * Date: 17/10/20 7:10 PM
 */
@RestController
public class EncodingRestController {
    private static List<EncodingRest> encodingRepo = new ArrayList<>();

    static {
        EncodingRest e1 = new EncodingRest("38400000-8cf0-11bd-b23e-10b96e4ef00d", "encoding1", "AWS");
        EncodingRest e2 = new EncodingRest(UUID.randomUUID().toString(), "encoding2", "AZURE");
        EncodingRest e3 = new EncodingRest(UUID.randomUUID().toString(), "encoding3", "GOOGLE");
        EncodingRest e4 = new EncodingRest(UUID.randomUUID().toString(), "encoding4", "AWS");
        encodingRepo.add(e1);
        encodingRepo.add(e2);
        encodingRepo.add(e3);
        encodingRepo.add(e4);
    }

    @GetMapping("encodings")
    @ResponseBody
    public ResponseEntity<Object> getEncodings() {
        System.out.println("Getting encodings..");
        if (encodingRepo.isEmpty()) {
            throw new CustomNotFoundException("No encodings available");
        }
        return new ResponseEntity<>(encodingRepo, HttpStatus.OK);
    }

    @GetMapping("encoding/{eId}")
    @ResponseBody
    public ResponseEntity<Object> getEncodings(@PathVariable("eId") String eId) {
        for (EncodingRest e : encodingRepo) {
            if (e.getId().equals(eId)) {
                return new ResponseEntity<>(e, HttpStatus.OK);
            }
        }
        System.out.println("No encodings available for the id : " + eId);
        throw new CustomNotFoundException("No encodings available for the id : " + eId);
    }

    @PostMapping(path = "encoding")
    public ResponseEntity createEncoding(@RequestBody EncodingRest e) {
        String uuid = UUID.randomUUID().toString();
        for (EncodingRest item : encodingRepo) {
            if (item.getId().equals(e)) {
                throw new CustomNotFoundException("Encoding Id repeated. Please retry ");
            }
        }
        e.setId(uuid);
        System.out.println(e.getId());
        encodingRepo.add(e);
        System.out.println(encodingRepo);
        return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);

    }

    @DeleteMapping("encoding/{eId}")
    @ResponseBody
    public ResponseEntity deleteEncoding(@PathVariable("eId") String eId) throws CustomNotFoundException {
        if (eId.isEmpty()) {
            throw new CustomNotFoundException("No Encoding Id available");
        }
        for (EncodingRest e : encodingRepo) {
            if (e.getId().equals(eId)) {
                encodingRepo.remove(e);
                System.out.println("Deleting Encoding with Id: " + eId + " successful.");

                return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
            }
        }
        System.out.println("Encoding with Id: " + eId + " Not Found");
        throw new CustomNotFoundException("Encoding with Id: " + eId + " Not Found");
    }

}

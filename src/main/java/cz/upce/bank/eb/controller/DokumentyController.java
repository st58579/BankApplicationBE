package cz.upce.bank.eb.controller;


import cz.upce.bank.eb.entity.Dokumenty;
import cz.upce.bank.eb.service.DokumentyServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

/**
 * Kontroler pro požadavky spojené s dokumenty
 */

@RestController
@RequestMapping("/api/dokumenty")
@CrossOrigin(origins = "*")
public class DokumentyController {

    @Autowired
    private DokumentyServis dokumentyServis;

    /**
     * Zpřístupnění obsahu dokumentu
     * @param documentId
     * @return dokument v binární formě
     */

    @RequestMapping(value = "/{documentId}", produces = "application/pdf", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getDocument(@PathVariable("documentId") Integer documentId){
        Dokumenty document =  dokumentyServis.getDocumentContentById(documentId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.setContentDispositionFormData("inline", document.getName() + ".pdf");
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<>(document.getContent(), headers, HttpStatus.OK);
        return response;
    }


    /**
     * Uložení nového dokumentu
     * @param name jméno
     * @param clientId id klienta
     * @param typeId id typu souboru
     * @param file samotný soubor
     * @return
     * @throws Exception
     */

    @PostMapping(value = "/novy", consumes = "multipart/form-data")
    public ResponseEntity saveNewDocument(@RequestPart("name") String name,
                                          @RequestPart("clientId") String clientId,
                                          @RequestPart("typeId") String typeId,
                                          @RequestPart(value = "file") MultipartFile file)
    throws Exception{
        dokumentyServis.saveNewDocument(name, clientId, typeId, file.getBytes());
        return new ResponseEntity(HttpStatus.OK);
    }


}

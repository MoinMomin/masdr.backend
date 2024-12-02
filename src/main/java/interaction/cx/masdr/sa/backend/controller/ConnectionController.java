package interaction.cx.masdr.sa.backend.controller;

import interaction.cx.masdr.sa.backend.mapper.ConnectionMapper;
import interaction.cx.masdr.sa.backend.service.DbConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/connection")
public class ConnectionController {
    @Autowired
    DbConnectionService dbConnectionService;
    @PostMapping("/createnewconnection")
    public ResponseEntity<Map> createNewConnection(@RequestBody ConnectionMapper connectionMapper){
        dbConnectionService.addNewDataSource(connectionMapper);
        return new ResponseEntity<>(HttpStatusCode.valueOf(201));
    }
}

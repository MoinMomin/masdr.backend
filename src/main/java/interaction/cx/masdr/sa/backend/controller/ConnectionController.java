package interaction.cx.masdr.sa.backend.controller;

import interaction.cx.masdr.sa.backend.mapper.ConnectionMapper;
import interaction.cx.masdr.sa.backend.service.DbConnectionService;
import interaction.cx.masdr.sa.backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/connection")
public class ConnectionController {
    @Autowired
    DbConnectionService dbConnectionService;
    @Autowired
    JwtUtil jwtUtil;
    @PostMapping("/createnewconnection")
    public ResponseEntity<Map> createNewConnection(@RequestBody ConnectionMapper connectionMapper,@RequestHeader("Authorization") String authorizationHeader){
        /*String token = authorizationHeader.replace("Bearer ", "");

        String tenantId = jwtUtil.getTenantIdFromToken(token);*/
        dbConnectionService.addNewDataSource(connectionMapper);
        return new ResponseEntity<>(HttpStatusCode.valueOf(201));
    }
    @PostMapping("/login")
    public String login(@RequestParam(required = false) String tenantId,@RequestParam String username){
        String token = jwtUtil.generateToken(tenantId, username);;
        return token;
    }
}

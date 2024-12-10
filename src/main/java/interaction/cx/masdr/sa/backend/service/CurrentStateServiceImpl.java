package interaction.cx.masdr.sa.backend.service;

import interaction.cx.masdr.sa.backend.model.CurrentState;
import interaction.cx.masdr.sa.backend.repository.CurrentStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CurrentStateServiceImpl implements CurrentStateService{
    @Autowired
    CurrentStateRepository currentStateRepository;
    @Override
    public CurrentState saveCurrentState(CurrentState currentState) {
        return currentStateRepository.save(currentState);
    }

    @Override
    public CurrentState findByTenantId(String tenantId) {
        Optional<CurrentState> currentStateOptional= currentStateRepository.findById(tenantId);
        if(currentStateOptional.isEmpty()){
            return null;
        }
        return currentStateOptional.get();
    }
}

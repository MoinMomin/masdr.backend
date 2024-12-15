package interaction.cx.masdr.sa.backend.service;

import interaction.cx.masdr.sa.backend.model.CurrentState;

import java.util.List;

public interface CurrentStateService {
    public CurrentState saveCurrentState(CurrentState currentState);
    public Object getConfiguration(String graphId);
}

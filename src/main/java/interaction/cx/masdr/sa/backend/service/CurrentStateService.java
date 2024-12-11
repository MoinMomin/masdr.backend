package interaction.cx.masdr.sa.backend.service;

import interaction.cx.masdr.sa.backend.model.CurrentState;

public interface CurrentStateService {
    public CurrentState saveCurrentState(CurrentState currentState);
    public CurrentState findByUserId(String userId);
}

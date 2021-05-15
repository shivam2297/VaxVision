package in.hideandseek.vaxvision.screens.stateinfo.view;

import java.util.ArrayList;
import java.util.List;

import in.hideandseek.vaxvision.common.IBaseView;
import in.hideandseek.vaxvision.common.libapi.statedistrict.model.District;
import in.hideandseek.vaxvision.common.libapi.statedistrict.model.State;

public interface ISelectStateView extends IBaseView {
    void onSuccessStates(List<State> states);
    void onSuccessDistricts(List<District> districts);
}

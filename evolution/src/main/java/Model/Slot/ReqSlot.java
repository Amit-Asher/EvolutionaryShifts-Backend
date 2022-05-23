package Model.Slot;

import Model.Range;
import Model.Role;

public class ReqSlot
{

    // todo: we should improve naming here and use PrfSlot properties.
    //  maybe we should change PrfSlot to be RoleBasedSlot and hold here RoleBasedSlot
    private Slot slot;
    private Role role;
    private Range personnelSize;

    public ReqSlot(Slot slot, Role role, Range personnelSize) {
        this.slot = slot;
        this.role = role;
        this.personnelSize = personnelSize;
    }


    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Range getPersonnelSize() {
        return personnelSize;
    }

    public void setPersonnelSize(Range personnelSize) {
        this.personnelSize = personnelSize;
    }
}

package Model.Slot;

import Model.Range;
import Model.Role;

public class ReqSlot
{
    protected Slot m_Slot;
    protected Role m_Role;
    protected Range m_PersonnelSize;

    public Slot getSlot() {
        return m_Slot;
    }

    public Role getRole() {
        return m_Role;
    }

    public Range getPersonnelSize() {
        return m_PersonnelSize;
    }

    public boolean equalsWithoutDay(ReqSlot reqSlot)
    {
        return this.m_Slot.getStartTime().equals(
                reqSlot.m_Slot.getStartTime()
                )&&
                this.m_Slot.getEndTime().equals(
                        reqSlot.m_Slot.getEndTime()
                )&&
                this.m_Role.equals(
                        reqSlot.m_Role
                )&&
                this.m_PersonnelSize.equals(reqSlot.m_PersonnelSize);
    }
}

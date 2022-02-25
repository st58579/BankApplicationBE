package mk.gridlib.domain;

import mk.gridlib.interfaces.objects.LovItem;

public class LovItemImpl<ID> implements LovItem<ID> {
    private ID id;
    private String label;

    public LovItemImpl(ID id, String label) {
        this.id = id;
        this.label = label;
    }

    public LovItemImpl() {
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}

package es.shwebill.domain.model;


public class OperatorItem {

    private String operatorName;
    private int operatorIcon;

    public OperatorItem(String operatorName, int operatorIcon) {
        this.operatorName = operatorName;
        this.operatorIcon = operatorIcon;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public int getOperatorIcon() {
        return operatorIcon;
    }
}

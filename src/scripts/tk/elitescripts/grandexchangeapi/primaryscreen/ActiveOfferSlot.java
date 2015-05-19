package scripts.tk.elitescripts.grandexchangeapi.primaryscreen;

import org.tribot.api2007.types.RSInterfaceComponent;


public class ActiveOfferSlot extends OfferSlot{

    private int itemID;
    private String itemName;
    private int itemPrice;
    private boolean completed = true;

    public ActiveOfferSlot(RSInterfaceComponent[] iFaceComponent) {
        super(iFaceComponent);
        RSInterfaceComponent[] children = this.getiFaceComponent();
        if(children!=null) {
            for (RSInterfaceComponent child : children) {
                int componentID = child.getComponentItem();
                String interfaceText = child.getText();
                if (componentID > -1) {
                    this.itemID = componentID;
                } else if (interfaceText != null) {
                    if (interfaceText.contains(" coin")) {
                        this.itemPrice = Integer.parseInt(interfaceText.replaceAll(" coin.*", ""));
                    }
                    else if(!(interfaceText.contains("Buy")||interfaceText.contains("Sell")||interfaceText.contains("Empty")) && interfaceText.length()>0){
                        this.itemName = interfaceText;
                    }
                }
                String [] actions = child.getActions();
                if(actions != null && actions.length>1){
                    completed = false;
                }
            }
        }
    }

    public int getItemID() {
        return itemID;
    }

    public boolean isCompleted(){
        return this.completed;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }
}


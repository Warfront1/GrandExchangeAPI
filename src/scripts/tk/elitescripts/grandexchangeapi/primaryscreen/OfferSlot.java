package scripts.tk.elitescripts.grandexchangeapi.primaryscreen;

import org.tribot.api2007.Interfaces;
import org.tribot.api2007.types.RSInterfaceChild;
import org.tribot.api2007.types.RSInterfaceComponent;
import org.tribot.api2007.types.RSInterfaceMaster;


public class OfferSlot {
    public enum type { SELL , BUY, EMPTY };
    private RSInterfaceComponent[] iFaceComponent;
    private type offerType;
    public OfferSlot(RSInterfaceComponent[] iFaceComponent){
        this.iFaceComponent=iFaceComponent;
        for(RSInterfaceComponent individualComponent: this.iFaceComponent){
            String componentText = individualComponent.getText();
            if(componentText!=null){
                if(componentText.contains("Buy")||componentText.contains("Sell")||componentText.contains("Empty")){
                    if(individualComponent.getHeight()>23){
                        if(componentText.contains("Buy")){
                            this.offerType= type.BUY;
                        }
                        else if(componentText.contains("Sell")){
                            this.offerType= type.SELL;
                        }
                        else if(componentText.contains("Empty")){
                            this.offerType= type.EMPTY;
                        }
                    }
                }
            }
        }
    }


    public type getType(){
        return this.offerType;
    }
    public type getOfferType() {
        return offerType;
    }

    public RSInterfaceComponent[] getiFaceComponent() {
        return iFaceComponent;
    }


}

package scripts.tk.elitescripts.grandexchangeapi.primaryscreen;

import org.tribot.api2007.types.RSInterface;
import org.tribot.api2007.types.RSInterfaceComponent;

/**
 * Created by joe on 5/8/2015.
 */
public class EmptyOfferSlot extends OfferSlot {

    private RSInterface buyOfferIFace;
    private RSInterface sellOfferIFace;
    private boolean enabled = false;

    public EmptyOfferSlot(RSInterfaceComponent[] iFaceComponent) {
        super(iFaceComponent);
        if(iFaceComponent!=null){
            for(RSInterfaceComponent individualComponent: iFaceComponent){
                String actions[] = individualComponent.getActions();
                if(actions!=null){
                    for(String action: actions){
                        if(action.contains("Buy")){
                            this.buyOfferIFace=individualComponent;
                        }
                        else if(action.contains("Sell")){
                            this.sellOfferIFace=individualComponent;
                        }
                    }
                }
            }
        }
        if(this.buyOfferIFace!=null && this.sellOfferIFace!=null){
            this.enabled = true;
        }
    }
    public boolean isEnabled(){
        return this.enabled;
    }

    public RSInterface getSellOfferIFace() {
        return sellOfferIFace;
    }

    public RSInterface getBuyOfferIFace() {
        return buyOfferIFace;
    }
}

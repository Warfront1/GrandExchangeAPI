package scripts.tk.elitescripts.grandexchangeapi;

import org.tribot.api2007.Interfaces;
import org.tribot.api2007.types.RSInterfaceChild;
import org.tribot.api2007.types.RSInterfaceComponent;
import org.tribot.api2007.types.RSInterfaceMaster;
import scripts.tk.elitescripts.grandexchangeapi.primaryscreen.ActiveOfferSlot;
import scripts.tk.elitescripts.grandexchangeapi.primaryscreen.EmptyOfferSlot;
import scripts.tk.elitescripts.grandexchangeapi.primaryscreen.OfferSlot;
import scripts.tk.elitescripts.grandexchangeapi.secondaryscreen.OfferScreen;

import java.util.ArrayList;


public class GrandExchange {
    private int interfaceMasterID = 465;
    private RSInterfaceMaster grandexchangeIFace;

    public GrandExchange(){
        this.grandexchangeIFace=Interfaces.get(this.interfaceMasterID);
    }

    public boolean isOpen(){
        return Interfaces.isInterfaceValid(this.interfaceMasterID) && this.grandexchangeIFace!=null && !this.grandexchangeIFace.isHidden();
    }
    public OfferSlot[] getAllOffers(){
        ArrayList<RSInterfaceComponent[]> iFaceArrayList = new ArrayList<RSInterfaceComponent[]>();
        ArrayList<RSInterfaceComponent[]> returnArrayList = new ArrayList<RSInterfaceComponent[]>();
        ArrayList<OfferSlot> finalreturnArrayList = new ArrayList<OfferSlot>();
        if(this.grandexchangeIFace!=null){
            RSInterfaceChild[] children = this.grandexchangeIFace.getChildren();
            if(children!=null) {
                for (RSInterfaceChild child : children) {
                    RSInterfaceComponent[] grandChildren = child.getChildren();
                    if (grandChildren != null) {
                        iFaceArrayList.add(grandChildren);
                    }
                }
            }
        }
        int counter = 0;
        for(RSInterfaceComponent[] iFaceComponents:iFaceArrayList){
            for(RSInterfaceComponent[] iFaceComponents2:iFaceArrayList){
                if(iFaceComponents2.length==iFaceComponents.length){
                    counter ++;
                    returnArrayList.add(iFaceComponents2);
                }
            }
            if(counter==6){
                // Found all Offer Boxes
                break;
            }
            counter = 0;
            returnArrayList = new ArrayList<RSInterfaceComponent[]>();
        }
        for(RSInterfaceComponent[] iFaceComponents3:returnArrayList){
            finalreturnArrayList.add(new OfferSlot(iFaceComponents3));
        }
        return finalreturnArrayList.toArray(new OfferSlot[finalreturnArrayList.size()]);
    }
    public boolean canCollect(){
        RSInterfaceComponent collectionButton = getCollectButton();
        return collectionButton!=null && !collectionButton.isHidden();
    }
    public RSInterfaceComponent getCollectButton(){
        if(this.grandexchangeIFace!=null){
            RSInterfaceChild[] children = this.grandexchangeIFace.getChildren();
            if(children!=null) {
                for (RSInterfaceChild child : children) {
                    RSInterfaceComponent[] grandChildren = child.getChildren();
                    if (grandChildren != null) {
                        for(RSInterfaceComponent individualgrandChild: grandChildren){
                            String iFaceText = individualgrandChild.getText();
                            if(iFaceText!=null){
                                if(iFaceText.contains("Collect")){
                                    return individualgrandChild;
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
    public ActiveOfferSlot[] getSellOffers(){
        ArrayList<ActiveOfferSlot> finalreturnArrayList = new ArrayList<ActiveOfferSlot>();
        for(OfferSlot individualOffer: getAllOffers()){
            if(individualOffer.getOfferType().equals(OfferSlot.type.SELL)){
                finalreturnArrayList.add(new ActiveOfferSlot(individualOffer.getiFaceComponent()));
            }
        }
        return finalreturnArrayList.toArray(new ActiveOfferSlot[finalreturnArrayList.size()]);
    }
    public ActiveOfferSlot[] getBuyOffers(){
        ArrayList<ActiveOfferSlot> finalreturnArrayList = new ArrayList<ActiveOfferSlot>();
        for(OfferSlot individualOffer: getAllOffers()){
            if(individualOffer.getOfferType().equals(OfferSlot.type.BUY)){
                finalreturnArrayList.add(new ActiveOfferSlot(individualOffer.getiFaceComponent()));
            }
        }
        return finalreturnArrayList.toArray(new ActiveOfferSlot[finalreturnArrayList.size()]);
    }
    public EmptyOfferSlot[] getEnabledEmptyOffers(){
        ArrayList<EmptyOfferSlot> finalreturnArrayList = new ArrayList<EmptyOfferSlot>();
        for(OfferSlot individualOffer: getAllOffers()){
            if(individualOffer.getOfferType().equals(OfferSlot.type.EMPTY)){
                EmptyOfferSlot emptyOfferSlotInstance = new EmptyOfferSlot(individualOffer.getiFaceComponent());
                if(emptyOfferSlotInstance.isEnabled()) {
                    finalreturnArrayList.add(new EmptyOfferSlot(individualOffer.getiFaceComponent()));
                }
            }
        }
        return finalreturnArrayList.toArray(new EmptyOfferSlot[finalreturnArrayList.size()]);
    }
    public EmptyOfferSlot[] getDisabledEmptyOffers(){
        ArrayList<EmptyOfferSlot> finalreturnArrayList = new ArrayList<EmptyOfferSlot>();
        for(OfferSlot individualOffer: getAllOffers()){
            if(individualOffer.getOfferType().equals(OfferSlot.type.EMPTY)){
                EmptyOfferSlot emptyOfferSlotInstance = new EmptyOfferSlot(individualOffer.getiFaceComponent());
                if(!emptyOfferSlotInstance.isEnabled()) {
                    finalreturnArrayList.add(new EmptyOfferSlot(individualOffer.getiFaceComponent()));
                }
            }
        }
        return finalreturnArrayList.toArray(new EmptyOfferSlot[finalreturnArrayList.size()]);
    }

//    SECOND SCREEN API
    private OfferScreen[] getSecondScreens(){
        ArrayList<OfferScreen> finalreturnArrayList = new ArrayList<OfferScreen>();
        if(this.grandexchangeIFace!=null){
            RSInterfaceChild[] children = this.grandexchangeIFace.getChildren();
            if(children!=null){
                for(RSInterfaceChild grandChild: children){
                    RSInterfaceComponent[] innerComponent = grandChild.getChildren();
                    if(innerComponent!=null){
                        OfferScreen Offer = new OfferScreen(innerComponent);
                        if(Offer.getScreenType()!=null) {
                            finalreturnArrayList.add(Offer);
                        }
                    }
                }
            }
        }
        return finalreturnArrayList.toArray(new OfferScreen[finalreturnArrayList.size()]);
    }

    public OfferScreen getSellScreen(){
        OfferScreen[] allOfferScreens = getSecondScreens();
        if(allOfferScreens!=null){
            for(OfferScreen individualOfferScreen:allOfferScreens){
                if(individualOfferScreen.getScreenType().equals(OfferScreen.screenType.SELL)){
                    return individualOfferScreen;
                }
            }
        }
        return null;
    }

    public OfferScreen getBuyScreen(){
        OfferScreen[] allOfferScreens = getSecondScreens();
        if(allOfferScreens!=null){
            for(OfferScreen individualOfferScreen:allOfferScreens){
                if(individualOfferScreen.getScreenType().equals(OfferScreen.screenType.BUY)){
                    return individualOfferScreen;
                }
            }
        }
        return null;
    }
    public RSInterfaceChild getBackButton(){
        if(this.grandexchangeIFace!=null){
            RSInterfaceChild[] children = this.grandexchangeIFace.getChildren();
            if(children!=null) {
                for (RSInterfaceChild child : children) {
                    String[] actions = child.getActions();
                    if(actions!=null){
                        for(String action: actions){
                            if(action.contains("Back")){
                                return child;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
    public boolean isBackButtonActive(){
        RSInterfaceChild backButton = getBackButton();
        if(backButton!=null){
            return !backButton.isHidden(true);
        }
        return false;
    }
}

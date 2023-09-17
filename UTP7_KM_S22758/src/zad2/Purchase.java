/**
 *
 *  @author Kilman Miłosz S22758
 *
 */


package zad2;


import java.beans.*;
import java.beans.PropertyVetoException;
public class Purchase {
    private String prod, data;
    private Double price;

    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private VetoableChangeSupport vetoableChangeSupport = new VetoableChangeSupport(this);

    public Purchase(String produkt, String data, Double cena) {
        this.prod = produkt;
        this.data = data;
        this.price = cena;
    }


    public void setData(String data) {
        String oldData = this.data;
        this.data = data;
        propertyChangeSupport.firePropertyChange("data", oldData, data);
    }

    @Override
    public String toString() {
        return "Purchase [prod=" + this.prod + ", data=" + this.data + ", price=" + this.price + "]";
    }

    public void setPrice(Double price) throws PropertyVetoException {
        Double oldPrice = this.price;
        vetoableChangeSupport.fireVetoableChange("price", oldPrice, price);
        this.price = price;
        propertyChangeSupport.firePropertyChange("price", oldPrice, price);
    }

    public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
    }

    public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        propertyChangeSupport.removePropertyChangeListener(propertyChangeListener);
    }

    public void addVetoableChangeListener(VetoableChangeListener vetoableChangeListener) {
        vetoableChangeSupport.addVetoableChangeListener(vetoableChangeListener);
    }

    public void removeVetoableChangeListener(VetoableChangeListener vetoableChangeListener) {
        vetoableChangeSupport.removeVetoableChangeListener(vetoableChangeListener);
    }
}

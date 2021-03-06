package br.org.indt.ndg.lwuit.model;

import br.org.indt.ndg.lwuit.ui.GeneralAlert;
import br.org.indt.ndg.mobile.Resources;

public class IntegerQuestion extends NumericQuestion {
    private long low;
    private long high;

   public void setHighConstraint(String _high) {
        try{
            high = Long.parseLong(_high);
        }
        catch(NumberFormatException nfe){
            high = Long.MAX_VALUE;
        }
    }

    public void setLowConstraint(String _low) {
        try{
            low = Long.parseLong(_low);
        }
        catch(NumberFormatException nfe){
            low = Long.MIN_VALUE;
        }
    }

    public String getType()
    {
        return "_int";
    }

     protected boolean passLowConstraint( String strValue ) {
        boolean result = true;
        if (strValue.equals("") ) result = true;
        else {
            try{
                double value = Long.parseLong(strValue);
                if (value >= low) result = true;
                else {
                    GeneralAlert.getInstance().addCommand(GeneralAlert.DIALOG_OK, true);
                    GeneralAlert.getInstance().show(Resources.INTEGER, Resources.VALUE_GREATER + low, GeneralAlert.WARNING);
                    result = false;
                }
            }
            catch(NumberFormatException ex)
            {
                GeneralAlert.getInstance().addCommand(GeneralAlert.DIALOG_OK, true);
                GeneralAlert.getInstance().show(Resources.INTEGER, Resources.NOTPROPERINTEGER +" \""+strValue+"\"", GeneralAlert.WARNING);
                result = false;
            }
        }
        return result;
    }

    protected boolean passHighConstraint( String strValue ) {
        boolean result = true;
        if ( strValue.equals("") ) result = true;
        else {
            try
            {
                double value = Long.parseLong(strValue);
                if (value <= high) result = true;
                else {
                    GeneralAlert.getInstance().addCommand(GeneralAlert.DIALOG_OK, true);
                    GeneralAlert.getInstance().show(Resources.INTEGER, Resources.VALUE_LOWER + high, GeneralAlert.WARNING);
                    result = false;
                }
            }
            catch(NumberFormatException ex)
            {
                GeneralAlert.getInstance().addCommand(GeneralAlert.DIALOG_OK, true);
                GeneralAlert.getInstance().show(Resources.INTEGER, Resources.NOTPROPERINTEGER +" \""+strValue+"\"", GeneralAlert.WARNING);
                result = false;
            }
        }
        return result;
    }

    public NDGAnswer getAnswerModel() {
        return new IntegerAnswer();
    }
}

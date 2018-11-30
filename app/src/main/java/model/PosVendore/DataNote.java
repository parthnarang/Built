package model.PosVendore;

public class DataNote
{
    String invoiceNo;
    String dateAndTime;
    String spending;

    public DataNote(String invoiceNo, String dateAndTime, String spending)
    {
        this.invoiceNo = invoiceNo;
        this.dateAndTime = dateAndTime;
        this.spending = spending;
    }

    public String getInvoiceNo()
    {
        return invoiceNo;
    }

    public String getDateAndTime()
    {
        return dateAndTime;
    }

    public String getSpending()
    {
        return spending;
    }
}
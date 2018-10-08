class Admin {

    void addCustomerRecordToInvoice(Customer customer, CustomerRecord record) {
        customer.invoice.add(record);
    }

}

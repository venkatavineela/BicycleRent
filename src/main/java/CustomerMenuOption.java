    public interface CustomerMenuOption {
        void execute(BicycleVendor vendor,Customer customer);

        CustomerMenuOption CheckOutBicycle = BicycleVendor::processCheckOut;

        CustomerMenuOption ReturnBicycle= BicycleVendor::processReturnBicycle;

        CustomerMenuOption Invoice= BicycleVendor::processInvoice;

        CustomerMenuOption Invalid= (vendor, customer) -> vendor.optionIsInValid();

    }


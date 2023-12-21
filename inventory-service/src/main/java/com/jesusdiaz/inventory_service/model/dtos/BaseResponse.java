package com.jesusdiaz.inventory_service.model.dtos;

/**
 * This class capture a list of errors when we want to create an order
 * but the items that we want don't exist in Inventory, or , they are not suficient.
 * @param errorMessages
 */
public record BaseResponse (String [] errorMessages){

    /**
     * Returns true if the BaseResponse contains no errors.
     * If there are no errors, it indicates that the order can be processed.
     * @return true if no errors are present, false otherwise
     */
    public boolean hasError() {
        return errorMessages!=null && errorMessages.length>0;
    }
}

//
//
// CLAIM REGISTER
//
//

/**
     * Finds and returns a list of claims matching the owner of given customer id.
     * @param customerId customer id of a claim owner
     * @return a list of claims matching the owner of the customer id
     */
    public List<Claim> findClaimsByCustomerId(int customerId) {
        // Creates an ArrayList which will be returned at the end of the method.
        List<Claim> result = new ArrayList<Claim>();
        // Creates an iterator for the list:
        Iterator<Claim> iterator = claims.iterator();
        // Runs through the whole list:
        while(iterator.hasNext()) {
            Claim claim = iterator.next();
            if (claim.getCustomerId() == customerId) {
                result.add(claim);
            }
        }
        // Returns null if no matches are found:
        if (result.isEmpty()) {
            return null;
        } else {
            // Returns the newly created list otherwise:
            return result;
        }
    }

    
    
/**
     * Returns the number of claims in this register.
     * @return 
     */
    public int size() {
        return claims.size();
    }



/**
     * Returns all the claims between the two dates in the parameter.
     * @param from
     * @param to
     * @return 
     */
    public List<Claim> claimsBetweenDates(Calendar from, Calendar to) {
        List<Claim> result = new ArrayList<Claim>();
        // Checks for every claim in this register:
        for (Claim claim : claims) {
            // The date of when the damage happened.
            Calendar date = claim.getDateHappened();
            // Checks if the claims date is inbetween the 2 dates specified in the parameter.
            if (date.after(from) && date.before(to)) {
                // Adds this claim to the return set:
                result.add(claim);
            }
        }
        // Returns a list containing all claims between correct dates:
        return result;
    }



public List<Claim> claimTypesBetweenDate(Class<?> type, Calendar from, Calendar to) {
        List<Claim> result = new ArrayList<Claim>();
        // Checks for every claim in this register:
        for (Claim claim : claims) {
            // The date of when the damage happened:
            Calendar date = claim.getDateHappened();
            // Checks if the claims date is inbetween the 2 dates specified in the parameter:
            if (date.after(from) && date.before(to)) {
                // Checks if the claim is of the class type specified in the parameter:
                if (type.isInstance(claim)) {
                    // Adds this claim to the return set:
                    result.add(claim);
                }
            }
        }
        // Returns a list containing all claims of specified type and between correct dates:
        return result;
    }



public List<Claim> claimTypes(Class<?> type) {
        List<Claim> result = new ArrayList<Claim>();
        // Checks for every claim in this register:
        for (Claim claim : claims) {
            // Checks if the claim is of the class type specified in the parameter:
            if (type.isInstance(claim)) {
                // Adds this claim to the return set:
                result.add(claim);
            }
        }
        // Returns a list containing all claims of specified type:
        return result;
    }



//
//
// CUSTOMER REGISTER
//
//

/**
     * Returns the first customer matching the given name.
     * @param name
     * @return 
     */
    public Customer findCustomerByName(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equals(name)) {
                return customer;
            }
        }
        return null;
    }



/**
     * Returns an ArrayList containing customers living at given address.
     * @param address
     * @return 
     */
    public ArrayList<Customer> findCustomersByAdress(Address address) {
        ArrayList<Customer> result = new ArrayList<Customer>();
        for (Customer customer : customers) {
            if (customer.getAddress().equals(address)) {
                result.add(customer);
            }
        }
        // Returns null if no matches are found:
        if (result.isEmpty()) {
            return null;
        } else {
            // Returns the newly created list otherwise:
            return result;
        }
    }



//
//
// EMPLOYEE REGISTER
//
//

/**
     * Deletes an employee from the register.
     * @param employee employee to be removed from register
     * @return true if this list has changed as a result of the call
     */
    public boolean deleteEmployee(Employee employee) {
        return employees.removeIf(i -> i.equals(employee));
    }



/**
     * Returns the number of employees in this register.
     * @return 
     */
    public int size() {
        return employees.size();
    }



//
//
// INSURANCE REGISTER
//
//

/**
     * Deletes an insurance from the list insurances.
     * 
     * @param insurance insurance to be removed from this list
     * @return true if this list changed as a result of the call
     */
    public boolean deleteInsurance(Insurance insurance) {
        return insurances.removeIf(i -> i.equals(insurance));
    }



/**
     * Returns the number of insurances of the specified type.
     * 
     * @param type the specified type of insurance
     * @return the number of insurances of the specified type
     */
    public int getNumberOfInsuranceByType(Class<?> type) {
        // Creates an integer which will be returned at the end of the method.
        int result = 0;
        // Creates an iterator for the list.
        Iterator<Insurance> iterator = insurances.iterator();
        // Runs through the whole list.
        while(iterator.hasNext()) {
            Insurance insurance = iterator.next();
            // Increases the result by one if the insurance is of the specified 
            // type.
            if(type.isInstance(insurance)) {
                result++;
            }
        }
        // Returns the result.
        return result;
    }



/**
     * Returns the yearly insurance premium of a customer with the specified 
     * customer id.
     * 
     * @param customerId the specified customer id
     * @return the yearly insurance premium of a customer
     */
    public int getTotalPremium(int customerId) {
        // Creates an integer which will be returned at the end of the method.
        int result = 0;
        // Creates an iterator for the list.
        Iterator<Insurance> iterator = insurances.iterator();
        // Runs through the whole list.
        while(iterator.hasNext()) {
            Insurance insurance = iterator.next();
            // Appends the yearly insurance premium of the insurance to the 
            // result if the insurance belongs to the customer with the 
            // specified customer id.
            if(insurance.getCustomerId() == customerId) {
                result += insurance.getPremium();
            }
        }
        // Returns the result.
        return result;
    }



/**
     * Returns the total yearly insurance premium of all customers.
     * 
     * @return the total yearly insurance premium
     */
    public int getTotalPremiumOfAll() {
        // Creates an integer which will be returned at the end of the method.
        int result = 0;
        // Creates an iterator for the list.
        Iterator<Insurance> iterator = insurances.iterator();
        // Runs through the whole list.
        while(iterator.hasNext()) {
            // Appends the yearly insurance premium of the insurance to the 
            // result.
            Insurance insurance = iterator.next();
            result += insurance.getPremium();
        }
        // Returns the result.
        return result;
    }



/**
     * Returns the total yearly insurance premium of the specified type of 
     * insurances.
     * 
     * @param type the specified type of insurances
     * @return the total yearly insurance premium of the type
     */
    public int getTotalPremiumOfAllByType(Class<?> type) {
        // Creates an integer which will be returned at the end of the method.
        int result = 0;
        // Creates an iterator for the list.
        Iterator<Insurance> iterator = insurances.iterator();
        // Runs through the whole list.
        while(iterator.hasNext()) {
            // Appends the yearly insurance premium of the insurance to the 
            // result if the insurance is of the specified type.
            Insurance insurance = iterator.next();
            if(type.isInstance(insurance)) {
                result += insurance.getPremium();
            }
        }
        // Returns the result.
        return result;
    }
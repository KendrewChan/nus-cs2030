import java.util.Comparator;

/**
 * This class is used to compare 2 <tt>Evt</tt> by their time and id.
 */
class EvtComparator implements Comparator<Evt> {
    /**
     * <p>Compare e1 and e2 by the time of their customers.</p>
     * <p>If their times are the same, then compare by their ids.</p>
     * @return <ul>
     *          <li>1 if e1 has larger time / id or if their id and time are the same</li>
     *          <li>-1 e2 has larger time / id
     *         </ul>
     */
    @Override
    public int compare(Evt e1, Evt e2) {
        Customer c1 = e1.getCustomer();
        Customer c2 = e2.getCustomer();
        if (c1.getTime() > c2.getTime()) {
            return 1;
        }

        if (c1.getTime() < c2.getTime()) {
            return -1;
        }

        if (c1.getId() > c2.getId()) {
            return 1;
        }

        if (c1.getId() < c2.getId()) {
            return -1;
        }
        
        return 1;
    }
}

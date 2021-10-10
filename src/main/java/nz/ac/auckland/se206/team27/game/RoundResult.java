package nz.ac.auckland.se206.team27.game;

/**
 * Result of a specific word / round.
 *
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public enum RoundResult {

    SKIPPED,
    PASSED,
    FAULTED,
    FAILED;


    /**
     * @return a capitalised version of the {@link RoundResult}'s name.
     */
    public String toCapitalised() {
        String enumName = this.name();
        return enumName.substring(0, 1).toUpperCase() + enumName.substring(1).toLowerCase();
    }

}

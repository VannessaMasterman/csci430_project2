package fsm;

/**
 * List of events for the state transitions
 */
public enum FSMEvent {
    LOGIN_CLIENT, // 0
    LOGIN_CLERK, // 1
    LOGIN_MANAGER, // 2
    VIEW_CLIENT, // 3
    VIEW_CLERK, // 4
    EXIT_VIEW, // 5 or 6 depending on previous state
    EXIT_PROGRAM, // not handled for transitions
    NO_EVENT // not handled
}

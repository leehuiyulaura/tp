package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Lesson;
import seedu.address.model.person.Person;

/**
 * Removed Lesson from contact.
 */
public class RemoveIndexCommand extends RemoveCommand {
    public static final String MESSAGE_REMOVE_LESSON_SUCCESS = "Removed Lesson for Person: %1$s";

    private Lesson lesson;
    private Index index;

    /**
     * constructor for new instance.
     *
     * @param lesson to be removed from the contact.
     * @param index of contact to remove lesson from.
     */
    public RemoveIndexCommand(Lesson lesson, Index index) {
        super();
        requireNonNull(lesson);
        requireNonNull(index);
        this.lesson = lesson;
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        personToEdit.removeLesson(lesson);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_REMOVE_LESSON_SUCCESS, lesson));
    }
}
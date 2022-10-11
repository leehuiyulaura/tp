package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import seedu.address.model.person.user.EmptyUser;
import seedu.address.model.person.user.ExistingUser;
import seedu.address.model.person.user.User;

import java.awt.*;
import java.util.Comparator;

public class UserProfile extends UiPart<Region> {

    private static final String FXML = "UserProfile.fxml";

    public final User user;

    @FXML
    private HBox userProfilePane;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label userLabel;
    @FXML
    private Label currModuleDescription;
    @FXML
    private Label prevModuleDescription;
    @FXML
    private Label planModuleDescription;
    @FXML
    private FlowPane currModulesTags;
    @FXML
    private FlowPane prevModulesTags;
    @FXML
    private FlowPane planModulesTags;

    public UserProfile(User user) {
        super(FXML);
        this.user = user;
        if (user instanceof ExistingUser) {
            userLabel.setText("User: ");
            name.setText(user.getName().fullName);
            phone.setText(user.getPhone().value);
            address.setText(user.getAddress().value);
            email.setText(user.getEmail().value);
            currModuleDescription.setText("Current Modules: ");
            prevModuleDescription.setText("Previous Modules: ");
            planModuleDescription.setText("Planned Modules: ");
            user.getCurrModules().stream()
                    .sorted(Comparator.comparing(mod -> mod.moduleName))
                    .forEach(mod -> currModulesTags.getChildren().add(new Label(mod.moduleName)));
            user.getPrevModules().stream()
                    .sorted(Comparator.comparing(mod -> mod.moduleName))
                    .forEach(mod -> prevModulesTags.getChildren().add(new Label(mod.moduleName)));
            user.getPlanModules().stream()
                    .sorted(Comparator.comparing(mod -> mod.moduleName))
                    .forEach(mod -> planModulesTags.getChildren().add(new Label(mod.moduleName)));
        } else {
            userLabel.setText("User details have not been added.");
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UserProfile)) {
            return false;
        }

        // state check
        UserProfile profile = (UserProfile) other;
        return user.equals(profile.user);
    }
}
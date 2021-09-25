package nz.ac.auckland.se206.team27.view.viewmodel;

/**
 * An interface between the model and the view. For interest, this is different
 * from the controller, as the {@code Controller} used in JavaFX is actually
 * more part of the view, than the traditional Controller's job description as
 * based on the MVC design pattern. The JavaFX {@code Controller} is only used
 * to provide bindings between the FXML files and the Java code.
 * <p>
 * {@link ViewModel}s create another level of abstraction between the model (our
 * business logic) and the FXML view/controller. These views/controllers will only
 * ever receive data to populate the view and manipulate the model through a
 * {@link ViewModel}.
 * <p>
 * Generally, there should be one {@link ViewModel} per controller / view (in
 * strictly MVVM architectures), but since there's not much complexity in our
 * uses, we have opted to combine some for ease of managing classes. Furthermore,
 * in order to keep our code simpler, we have eliminated two-way data-bindings
 * between the {@code View} and {@code ViewModel}.
 * <p>
 * @see <a href="https://github.com/sialcasa/mvvmFX/wiki/MVVM-in-more-detail">MVVM</a>
 *
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public interface ViewModel {}

# Working Log
Matthew Jakeman (mjak923)

## 17 September
Implemented a text-to-speech queuing system using Festival. This mainly
resides in a class called `SpeechManager`, and uses the JavaFX Task API
along with an `ExecutorService` in order to run festival in the background.

The idea behind this system was to make each queued 'phrase' (where a phrase
is some text to be said) into a self-contained task, and run these tasks
sequentially. This should result in no phrases overlapping with each other,
as one task must be completely done before another can start.

One issue I encountered was that tasks were running indefinitely. The solution
to this was straightforward (festival needed to be run in batch mode rather
than interactive mode, by supplying the flag '-b'). However, it exposed a flaw
in that is festival were to hang for some reason, it would 'take the rest of the
program' down with it. This is what led me to adding timeouts and comprehensive
exception handling to make the SpeechManager recoverable and robust.

Another feature I wanted was the ability to clear the queue at any point in time,
finishing only the current task and then stopping. While I initially used the
`ExecutorService.shutdown()` method, this was more of a hack than a robust solution
as the ExecutorService would need to be recreated from scratch to submit more
tasks.

My solution was to instead create the queue independently (I used a LinkedBlockingQueue
as this is what the single threaded executor uses according to Java docs) and then
manually constructed a ThreadPoolExecutor with only one thread. I could now clear
the queue independently of the executor in a thread-safe manner.

One 'bonus' feature I added was the ability for the task to set a status message (either
'Running', 'Cancelled', 'Failure', or 'Success') depending on various factors such as
the exit code from the festival process or whether the thread is interrupted. This mainly
helped with debugging and testing the task implementation.

The last feature I attempted was to add playback speed variability. This is accomplished
through the 'Duration_Stretch' property in festival which slows down the playback
accordingly. As the notion of 'stretch' is opposite to 'speed', I took the reciprocal of
the provided speed value to find the stretch multiplier. This is done on a per-task
basis, however it might be worth having some kind of global 'default speed' setting.

I am quite happy with the result overall. The SpeechManager class is robust and
very fast, and meets the required goals of supporting task queuing and the clearing
of said queue. Additionally, I was able to implement speed variability in a fairly
unobtrusive manner.

## 18 September
Fixed some minor issues (primarily related to code style) after feedback from teammates. Had
a team meeting and delegated the next round of tasks (see [minutes](minutes-18-09-21.md)).

## 20 September
Add support for Inter font family. The font 'Inter' is a sans-serif font focused on user-interfaces,
emphasising readability. It is a good fit for Kemu Kupu. The font is added using the FontResource
system designed by Raymond - my teammate. The default font is set to 'Inter 11' using CSS. I have added
all 19 styles of Inter for the meantime, but this can be reduced when we are confident certain styles will
not be used.

I also added some simple styling to the main menu, colouring it a turquoise blue and adding
white-outlined buttons that mostly reflect the initial design. There are no animations, transitions,
or hover/focus states implemented at this time.

Next, I created two new views for topic selection and topic previewing. I added some basic styling but quickly
ran into problems with namespacing css selectors, and having breakages in other parts of the application.
After discussing with Jordan, I decided to split the CSS files into one master stylesheet plus several view
stylesheets (representing one view, or a few related views).

I also had to rework how stylesheets apply styles regarding the "root" style class. I opted to remove all manual
mentions of this class and just style the scene root. One consequence was that padding was broken. I added
a "container" style class to rectify this.

## 21 September
Took the day off!

## 22 September
### Views and Styling
Added hover and focus states to the menu buttons. They now have a translucent black fill when the user hovers,
which darkens when the user presses the button. I chose to use a dark tint (vs white) as this will play better
with a dynamic/animated background if or when I get around to implementing that. I also felt the white tint
was "too strong". I changed the background gradient to use a darker shade of blue.

Next, I worked on adding the split view to the topic preview screen. This consists of an even split between
the menu content (topic name + description and controls) and an image which takes up the other half of the
screen. I initially tried using an `HBox` but I couldn't get the sizing to work reliably. I instead opted to
use a `TilePane`.

I also reworked the FXML layouts to use different and more appropriate root elements (previously, every root
element was an AnchorPane). I added some additional CSS for shadows behind the menu title and buttons.

I changed the image pane from being a `Region` to an `AnchorPane`, and anchored a label with a semi-transparent
black fill to act as a small 'credit tag'. This will show copyright information about the image (or rather,
attribution as we intend to use Creative-Commons licensed images, or our own).

I applied the split view to the preferences screen as well, but encountered some further issues with sizing. I
decided to rework the split view using `GridPane` instead of `TilePane` to make things more robust. This ended
up being far cleaner and more reliable to work with.

Lastly, I removed the temporary logo we had been using from the menu and topic selection screen.

### Transitions
I began working on transitions, which is my long-term goal for beyond A3. I wanted to try implement a subset of
the final transition and complex layout system for A3 as a proof-of-concept.

The transition framework architecture I came up with is as follows:

Views (which must use a `BaseController`-derived class as their FX Controller) can specify what happens when
the view is loaded. By overriding the `defaultOnEnter()` method, views can define their own transitions and
specify what the default transition should be.

SceneLoader now optionally expects a callback method which can override this default behaviour. This allows
for different transition types such as the home screen zooming on startup, but sliding on subsequent visits.

All transition code is now encapsulated inside a TransitionBuilder class which can generate some preset
transitions with limited configurability.

### Meeting
Second team meeting [(minutes)](minutes-22-09-21.md). We made a list of oustanding tasks and action points, as well as some design decisions after
Tuesday's design lecture. I was able to get my transitions PR reviewed and merged it after a few changes.

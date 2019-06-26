package jp.wasabeef.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.util.List;
import jp.wasabeef.richeditor.RichEditor;

public class MainActivity extends AppCompatActivity implements RichEditor.ReceivedCaret {

    private RichEditor mEditor;
    private TextView mPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditor = (RichEditor) findViewById(R.id.editor);
        mEditor.setHtml("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque egestas varius <b>felis</b>, porttitor porttitor neque. Vivamus "
                + "placerat purus at purus porta, eget faucibus nisl dignissim. Class aptent taciti sociosqu ad litora torquent per conubia nostra,"
                + " per inceptos himenaeos. Nam eros neque, blandit eget lobortis vel, imperdiet a augue. Praesent vel tempus tellus. Etiam aliquet"
                + " leo id mauris ultricies, quis facilisis ipsum molestie. Aliquam pretium mollis ante, sed consectetur justo blandit sit amet. "
                + "Vestibulum eget ex pharetra, laoreet risus in, dictum arcu. Nulla feugiat sapien quis mattis feugiat. Proin efficitur "
                + "pellentesque tincidunt. Vestibulum porttitor ligula ligula, dapibus ultrices nisl fermentum et. Suspendisse consequat, massa sit"
                + " amet volutpat vestibulum, lorem metus porta erat, a volutpat augue nisl eu nibh. Nunc sed erat eu augue eleifend laoreet. "
                + "Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae;\n"
                + "\n"
                + "Aliquam suscipit justo id imperdiet lobortis. Sed ullamcorper, elit congue blandit dignissim, lectus lorem porta mauris, id "
                + "commodo mi ante ut massa. Curabitur sollicitudin tincidunt lacinia. Aenean vitae sodales nisl. Sed vel massa eros. Suspendisse "
                + "nisl diam, faucibus vitae scelerisque eu, porta ut neque. Orci varius natoque penatibus et magnis dis parturient montes, "
                + "nascetur ridiculus mus. Ut vitae faucibus ante. Vestibulum volutpat consectetur mauris, id consectetur felis malesuada sed. In "
                + "luctus dui quam, at tincidunt mauris congue sit amet. Fusce egestas blandit ante eget sagittis. Duis malesuada sollicitudin nisi"
                + ". Duis convallis molestie nisi, sed commodo turpis tristique at.\n"
                + "\n"
                + "Nunc porta est ut lacus tincidunt, porttitor efficitur magna pellentesque. Nunc posuere dui eget nulla imperdiet, eu tincidunt "
                + "justo pellentesque. Nam venenatis sapien vel velit congue, id scelerisque lacus sagittis. Vivamus imperdiet laoreet porttitor. "
                + "Donec dignissim ligula eget odio vestibulum, lacinia finibus est eleifend. Praesent eros mi, dapibus nec ultrices non, accumsan "
                + "in risus. Donec sodales pretium dolor commodo laoreet. Quisque vel leo mollis, tristique lorem et, auctor augue. Ut non volutpat"
                + " diam. Nullam ut enim eu nisi ullamcorper tempus id et libero. Sed feugiat iaculis tellus, scelerisque facilisis diam pretium ac"
                + ". Phasellus lobortis vel erat at pretium. Nulla maximus erat a molestie mattis. Suspendisse congue dictum ex quis tincidunt. Ut "
                + "volutpat purus vel malesuada ultrices.\n"
                + "\n"
                + "Ut quis gravida nisl, vel imperdiet mauris. Mauris vehicula odio sit amet augue commodo rutrum. Vivamus viverra mollis molestie."
                + " Vivamus et nibh et tortor auctor suscipit. Aliquam lobortis hendrerit sem, in malesuada dolor finibus a. Pellentesque aliquet "
                + "nisl non ex faucibus, ut cursus leo ullamcorper. Morbi ultrices mattis diam, non rutrum neque. Donec vitae sagittis leo, eget "
                + "sollicitudin odio. Morbi est justo, lobortis ac placerat at, accumsan ac enim. Etiam at posuere lorem, sit amet fermentum ex. "
                + "Maecenas accumsan, justo et sagittis imperdiet, nisl leo maximus nulla, nec elementum metus libero ac leo.\n"
                + "\n"
                + "Aenean ac lorem vel elit maximus venenatis. Curabitur maximus porttitor nunc ac sollicitudin. Class aptent taciti sociosqu ad "
                + "litora torquent per conubia nostra, per inceptos himenaeos. Praesent interdum odio eu ligula feugiat scelerisque. Nunc vitae "
                + "nulla purus. Cras vitae mi non risus aliquam vestibulum. ANH neque dolor, egestas at eleifend vel, gravida sed urna. Praesent "
                + "dictum sem leo, quis congue ");
        //mEditor.setEditorBackgroundColor(Color.BLUE);
        //mEditor.setBackgroundColor(Color.BLUE);
        //mEditor.setBackgroundResource(R.drawable.bg);
        mEditor.replaceContentIfLinkExist();
        mEditor.setPadding(10, 10, 10, 10);
        //mEditor.setBackground("https://raw.githubusercontent.com/wasabeef/art/master/chip.jpg");
        mEditor.setPlaceholder("Insert text here...");
        mEditor.setInputEnabled(false);

        mPreview = (TextView) findViewById(R.id.preview);
        mEditor.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
            @Override
            public void onTextChange(String text) {
                mPreview.setText(text);
            }
        });
        mEditor.setOnLinkClickListener(new RichEditor.OnLinkClickListener() {

            @Override
            public void onLinkClicked(String link) {
            }
        });
        mEditor.setOnReceivedValue(this);

        mEditor.setOnDecorationChangeListener(new RichEditor.OnDecorationStateListener() {
            @Override
            public void onStateChangeListener(String text, List<RichEditor.Type> types) {
            }
        });

        findViewById(R.id.action_undo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mEditor.undo();
                mEditor.focusAtPoint(100, 50);
            }
        });

        findViewById(R.id.action_redo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setCaret(85);
            }
        });

        findViewById(R.id.action_bold).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setBold();
            }
        });

        findViewById(R.id.action_italic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setItalic();
            }
        });

        findViewById(R.id.action_subscript).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setSubscript();
            }
        });

        findViewById(R.id.action_superscript).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setSuperscript();
            }
        });

        findViewById(R.id.action_strikethrough).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setStrikeThrough();
            }
        });

        findViewById(R.id.action_underline).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setUnderline();
            }
        });

        findViewById(R.id.action_heading1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setHeading(1);
            }
        });

        findViewById(R.id.action_heading2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setHeading(2);
            }
        });

        findViewById(R.id.action_heading3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setHeading(3);
            }
        });

        findViewById(R.id.action_heading4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setHeading(4);
            }
        });

        findViewById(R.id.action_heading5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setHeading(5);
            }
        });

        findViewById(R.id.action_heading6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setHeading(6);
            }
        });

        findViewById(R.id.action_txt_color).setOnClickListener(new View.OnClickListener() {
            private boolean isChanged;

            @Override
            public void onClick(View v) {
                mEditor.setTextColor(isChanged ? Color.BLACK : Color.RED);
                isChanged = !isChanged;
            }
        });

        findViewById(R.id.action_bg_color).setOnClickListener(new View.OnClickListener() {
            private boolean isChanged;

            @Override
            public void onClick(View v) {
                mEditor.setTextBackgroundColor(isChanged ? Color.TRANSPARENT : Color.YELLOW);
                isChanged = !isChanged;
            }
        });

        findViewById(R.id.action_indent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setIndent();
            }
        });

        findViewById(R.id.action_outdent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setOutdent();
            }
        });

        findViewById(R.id.action_align_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setAlignLeft();
            }
        });

        findViewById(R.id.action_align_center).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setAlignCenter();
            }
        });

        findViewById(R.id.action_align_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setAlignRight();
            }
        });

        findViewById(R.id.action_blockquote).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setBlockquote();
            }
        });

        findViewById(R.id.action_insert_bullets).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setBullets();
            }
        });

        findViewById(R.id.action_insert_numbers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setNumbers();
            }
        });

        findViewById(R.id.action_insert_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.insertImage("http://www.1honeywan.com/dachshund/image/7.21/7.21_3_thumb.JPG", "dachshund");
            }
        });

        findViewById(R.id.action_insert_link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mEditor.insertLink("www.github.com/wasabeef", "wasabeef");
                mEditor.focusAtPoint(297, 496);
            }
        });
        findViewById(R.id.action_insert_checkbox).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.insertTodo();
            }
        });
    }

    @Override
    public void onGetCaretSuccess (int value) {
        Log.d("AAA", "Caret:" + value);
    }
}

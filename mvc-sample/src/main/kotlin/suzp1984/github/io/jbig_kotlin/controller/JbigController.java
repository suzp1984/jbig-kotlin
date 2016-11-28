package suzp1984.github.io.jbig_kotlin.controller;

import android.graphics.Bitmap;
import android.util.Log;

import com.squareup.otto.Subscribe;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.internal.Preconditions;
import io.github.suzp1984.jbig_android_library.JbigCodec;
import io.github.suzp1984.jbig_android_library.JbigCodecFactory;
import suzp1984.github.io.jbig_kotlin.states.JbigDbState;
import suzp1984.github.io.jbig_kotlin.utils.ByteUtils;

/**
 * Created by suzhenxi on 11/28/2016.
 */

@Singleton
public class JbigController extends
        BaseUiController<JbigController.JbigControllerUi, JbigController.JbigUiCallback> {

    private final JbigDbState mJbigDbState;

    @Inject
    public JbigController(JbigDbState state) {
        mJbigDbState = Preconditions.checkNotNull(state, "JbigDbState is not null.");
    }

    @Override
    JbigUiCallback createUiCallbacks(JbigControllerUi ui) {
        return new JbigUiCallback() {
            @Override
            public void encodeBitmap(Bitmap bitmap) {
                Bitmap[] bitmaps = new Bitmap[1];
                bitmaps[0] = bitmap;

                JbigCodec jbigCodec = JbigCodecFactory.INSTANCE.getJbigCodec(JbigCodecFactory.CODEC.JNI_CODEC);

                if (jbigCodec != null) {
                    Byte[] jbigData = jbigCodec.encode(bitmaps);

                    mJbigDbState.putJbig(jbigData);

                    String serializedJbig = ByteUtils.byteArray2HexString(jbigData);
                    Log.e("Encode", serializedJbig);
                }
            }

            @Override
            public void delete(int position) {

            }

            @Override
            public void add(byte[] jbig) {
                mJbigDbState.putJbig(jbig);
            }
        };
    }

    @Override
    void onUiAttached(JbigControllerUi ui) {

    }

    @Override
    void onUiDetached(JbigControllerUi ui) {

    }

    @Override
    void populateUi(JbigControllerUi ui) {
        Log.e("JbigController", "popularUi");
        if (ui instanceof JbigEncoderUi) {
            populateUi((JbigEncoderUi) ui);
        } else if (ui instanceof  JbigDecoderUi) {
            populateUi((JbigDecoderUi) ui);
        }
    }

    @Override
    void onInited() {
        mJbigDbState.registerForEvents(this);
    }

    @Override
    void onSuspended() {
        mJbigDbState.unregisterForEvents(this);
    }

    @Subscribe
    public void onJbigDataAdd(JbigDbState.JbigDbAddEvent event) {
        Log.e("JbigController", "onJbigDataAdd");
        populateUis();
    }

    private void populateUi(JbigDecoderUi ui) {
        Log.e("JbigController", "populateUi JbigDecoderUi");
        JbigCodec jbigCodec = JbigCodecFactory.getJbigCodec(JbigCodecFactory.CODEC.JNI_CODEC);

        if (jbigCodec != null) {
            for (byte[] jbig : mJbigDbState.getJbigDbs()) {
                Bitmap[] bms = jbigCodec.decode(jbig);
                ui.showBitmaps(bms);
            }
        }
    }

    private void populateUi(JbigEncoderUi ui) {
        // nothing, pass
    }

    public interface JbigControllerUi extends BaseUiController.Ui<JbigUiCallback> {

    }

    public interface JbigDecoderUi extends JbigControllerUi {
        void showJbig(byte[] jbig);
        void showBitmaps(Bitmap[] bitmaps);
    }

    public interface JbigEncoderUi extends JbigControllerUi {
        // nothing?
    }

    public interface JbigUiCallback {
        void encodeBitmap(Bitmap bitmap);
        void delete(int position);
        void add(byte[] jbig);
    }
}
package android.shokusuka.miurahack.com.shokusuka.facebook

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facebook.CallbackManager
import com.facebook.share.model.ShareLinkContent
import com.facebook.share.widget.ShareDialog

class FacebookShareFragment : Fragment() {
    val callbackManager: CallbackManager = CallbackManager.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val shareDialog: ShareDialog = ShareDialog(this)

        if (ShareDialog.canShow(ShareLinkContent::class.java)) {
            shareDialog.show(
                    ShareLinkContent.Builder()
                            .setContentTitle("タイトル")
                            .setContentDescription("詳細")
                            .setContentUrl(Uri.parse("URL"))
                            .build()
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}

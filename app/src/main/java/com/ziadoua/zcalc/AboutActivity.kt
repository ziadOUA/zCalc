package com.ziadoua.zcalc

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ziadoua.zcalc.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Themes
        val themes = Themes(this)
        themes.applyDayNightOverride()
        setTheme(themes.getTheme())

        // Change the status bar color
        if (MyPreferences(this).theme == 1) { // Amoled theme
            window.statusBarColor = ContextCompat.getColor(this, R.color.amoled_background_color)
        } else {
            window.statusBarColor = ContextCompat.getColor(this, R.color.background_color)
        }

        binding = ActivityAboutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Set app version
        val versionName =  this.getString(R.string.app_version_title) + " "+ BuildConfig.VERSION_NAME
        binding.aboutAppVersion.text = versionName

        // back button
        binding.aboutBackButton.setOnClickListener {
            finish()
        }

        // Translate
        binding.aboutTranslate.setOnClickListener {
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://hosted.weblate.org/engage/opencalc/")
            )
            startActivity(browserIntent)
        }

        // Rate
        binding.aboutRate.setOnClickListener {
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=com.darkempire78.opencalculator")
            )
            startActivity(browserIntent)
        }

//        // Tip
//        binding.aboutDonate.setOnClickListener {
//            val browserIntent = Intent(
//                Intent.ACTION_VIEW,
//                Uri.parse("https://www.paypal.me/ImDarkempire")
//            )
//            startActivity(browserIntent)
//        }

        // Github
        binding.aboutGithub.setOnClickListener {
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://github.com/ziadOUA/zCalc")
            )
            startActivity(browserIntent)
        }

//        // Discord
//        binding.aboutDiscord.setOnClickListener {
//            val browserIntent = Intent(
//                Intent.ACTION_VIEW,
//                Uri.parse("https://discord.com/invite/sPvJmY7mcV")
//            )
//            startActivity(browserIntent)
//        }

        binding.aboutPrivacyPolicy.setOnClickListener {
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://github.com/ziadOUA/zCalc/blob/master/PRIVACY.md")
            )
            startActivity(browserIntent)
        }


        var clickAppVersionCount = 0
        binding.aboutAppVersion.setOnClickListener {
            clickAppVersionCount++
            if (clickAppVersionCount > 3) {
                Toast.makeText(this, this.getString(R.string.thanks_for_using_opencalc), Toast.LENGTH_SHORT).show()
                clickAppVersionCount = 0
            }
        }
    }
}

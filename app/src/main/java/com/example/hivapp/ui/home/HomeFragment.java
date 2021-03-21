package com.example.hivapp.ui.home;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.hivapp.R;
import com.example.hivapp.ui.Info.CustomDialogClass;
import com.example.hivapp.ui.book.BookFragment;
import com.example.hivapp.ui.store.StoreFragment;

public class HomeFragment extends Fragment {

Button btn1,btn2,btn3,btn4,btn5,btn6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.home_fragment, container, false);

        btn1= root.findViewById(R.id.whatishiv);
        btn2= root.findViewById(R.id.hivtransmission);
        btn3= root.findViewById(R.id.hivconnection);
        btn5= root.findViewById(R.id.infobook);
        btn6= root.findViewById(R.id.infomedication);

        btn5.setOnClickListener(v->{
            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container,
                    new BookFragment()).commit();
        });
        btn6.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container,
                    new StoreFragment()).commit();
        });

        String what="HIV is a virus that damages the immune system. Untreated HIV affects and kills CD4 cells, which are a type of immune cell called T cell.\n" +
                "\n" +
                "Over time, as HIV kills more CD4 cells, the body is more likely to get various types of conditions and cancers.\n" +
                "\n" +
                "HIV is transmitted through bodily fluids that include:\n" +
                "\n" +
                "blood\n" +
                "semen\n" +
                "vaginal and rectal fluids\n" +
                "breast milk\n" +
                "The virus isn’t transferred in air or water, or through casual contact.\n" +
                "\n" +
                "Because HIV inserts itself into the DNA of cells, it’s a lifelong condition and currently there’s no drug that eliminates HIV from the body, although many scientists are working to find one.\n" +
                "\n" +
                "However, with medical care, including treatment called antiretroviral therapy, it’s possible to manage HIV and live with the virus for many years.\n" +
                "\n" +
                "Without treatment, a person with HIV is likely to develop a serious condition called the Acquired Immunodeficiency Syndrome, known as AIDS.\n" +
                "\n" +
                "At that point, the immune system is too weak to successfully respond against other diseases, infections, and conditions.\n" +
                "\n" +
                "Untreated, life expectancy with end stage AIDS is about 3 yearsTrusted Source. With antiretroviral therapy, HIV can be well-managed, and life expectancy can be nearly the same as someone who has not contracted HIV.\n" +
                "\n" +
                "It’s estimated that 1.2 million Americans are currently living with HIV. Of those people, 1 in 7 don’t know they have the virus.\n" +
                "\n" +
                "HIV can cause changes throughout the body.";

        btn1.setOnClickListener(v -> {
            CustomDialogClass cdd = new CustomDialogClass(getActivity());
           //cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor(R.color.white)));
            cdd.show();
            cdd.setMessage(what);
        });
        String transmit="Anyone can contract HIV. The virus is transmitted in bodily fluids that include:\n" +
                "\n" +
                "blood\n" +
                "semen\n" +
                "vaginal and rectal fluids\n" +
                "breast milk\n" +
                "Some of the ways HIV is transferred from person to person include:\n" +
                "\n" +
                "through vaginal or anal sex — the most common route of transmission\n" +
                "by sharing needles, syringes, and other items for injection drug use\n" +
                "by sharing tattoo equipment without sterilizing it between uses\n" +
                "during pregnancy, labor, or delivery from a pregnant person to their baby\n" +
                "during breastfeeding\n" +
                "through “premastication,” or chewing a baby’s food before feeding it to them\n" +
                "through exposure to the blood, semen, vaginal and rectal fluids, and breast milk of someone living with HIV, such as through a needle stick\n" +
                "The virus can also be transmitted through a blood transfusion or organ and tissue transplant. However, rigorous testing for HIV among blood, organ, and tissue donors ensures that this is very rare in the United States.\n" +
                "\n" +
                "It’s theoretically possible, but considered extremely rare, for HIV to be transmitted through:\n" +
                "\n" +
                "oral sex (only if there are bleeding gums or open sores in the person’s mouth)\n" +
                "being bitten by a person with HIV (only if the saliva is bloody or there are open sores in the person’s mouth)\n" +
                "contact between broken skin, wounds, or mucous membranes and the blood of someone living with HIV\n" +
                "HIV does NOT transfer through:\n" +
                "\n" +
                "skin-to-skin contact\n" +
                "hugging, shaking hands, or kissing\n" +
                "air or water\n" +
                "sharing food or drinks, including drinking fountains\n" +
                "saliva, tears, or sweat (unless mixed with the blood of a person with HIV)\n" +
                "sharing a toilet, towels, or bedding\n" +
                "mosquitoes or other insects\n" +
                "It’s important to note that if a person living with HIV is being treated and has a persistently undetectable viral load, it’s virtually impossible to transmit the virus to another person.";
        btn2.setOnClickListener(v -> {
            CustomDialogClass cdd = new CustomDialogClass(getActivity());
            //cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            cdd.show();
            cdd.setMessage(transmit);});

        String conn="To develop AIDS, a person has to have contracted HIV. But having HIV doesn’t necessarily mean that someone will develop AIDS.\n" +
                "\n" +
                "Cases of HIV progress through three stages:\n" +
                "\n" +
                "stage 1: acute stage, the first few weeks after transmission\n" +
                "stage 2: clinical latency, or chronic stage\n" +
                "stage 3: AIDS\n" +
                "As HIV lowers the CD4 cell count, the immune system weakens. A typical adult’s CD4 count is 500 to 1,500 per cubic millimeter. A person with a count below 200 is considered to have AIDS.\n" +
                "\n" +
                "How quickly a case of HIV progresses through the chronic stage varies significantly from person to person. Without treatment, it can last up to a decade before advancing to AIDS. With treatment, it can last indefinitely.\n" +
                "\n" +
                "There’s currently no cure for HIV, but it can be managed. People with HIV often have a near-normal lifespan with early treatment with antiretroviral therapy.\n" +
                "\n" +
                "Along those same lines, there’s technically no cure for AIDS currently. However, treatment can increase a person’s CD4 count to the point where they’re considered to no longer have AIDS. (This point is a count of 200 or higher.)\n" +
                "\n" +
                "Also, treatment can typically help manage opportunistic infections.\n" +
                "\n" +
                "HIV and AIDS are related, but they’re not the same thing.\n" +
                "\n" +
                "Learn more about the difference between HIV and AIDS.";

        btn3.setOnClickListener(v -> {
            CustomDialogClass cdd = new CustomDialogClass(getActivity());
            //cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            cdd.show();
            cdd.setMessage(conn);});

        return root;
    }
}
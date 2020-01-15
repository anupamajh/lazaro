//
//  GuestJiniAlerBox.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 28/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GuestJiniAlerBox: View {
    @Binding var showAlert: Bool
    @Binding var alertTitle:String
    @Binding var alertBody:String
    
    var body: some View {
        VStack{
            VStack{
                HStack{
                    GuestJiniTitleText(title: self.alertTitle)
                    Spacer()
                }.padding()
                HStack{ GuestJiniInformationText(information: self.alertBody)
                }.padding()
                HStack{
                    Spacer()
                    Button(action: {
                        self.showAlert = false
                        
                    }) {
                        GuestJiniRoundButtonSystemImage(systemImage: "checkmark")
                    }
                    
                    Spacer()
                }.padding()
            }.background(Color.white)
                .cornerRadius(15)
                .padding(.all,15)
            
        }.shadow(radius: 10)
            .padding()
            .opacity(1)
        
    }
}

struct GuestJiniAlerBox_Previews: PreviewProvider {
    static var previews: some View {
        GuestJiniAlerBox(showAlert: .constant(true),
                         alertTitle: .constant("Forgot Password"),
                         alertBody: .constant("A link has been sent to your email account to reset your password.")
        )
    }
}

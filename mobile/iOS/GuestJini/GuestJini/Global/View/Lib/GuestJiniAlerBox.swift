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
    var alertTitle:String
    var alertBody:String
    
    var body: some View {
        GeometryReader{geometry in
            VStack{
                VStack{
                    VStack{
                        HStack{
                            GuestJiniTitleText(title: self.alertTitle)
                            Spacer()
                        }.padding()
                        HStack{ GuestJiniInformationText(information: self.alertBody)
                        }.padding(.vertical)
                        HStack{
                            Spacer()
                            Button(action: {
                                self.showAlert = false
                            }) { GuestJiniRoundButtonText(imageName: "round-done")
                            }
                            Spacer()
                        }
                    }.background(Color.white)
                        .cornerRadius(15)
                        .padding(.all,15)
                    
                }.shadow(radius: 10)
                    .padding()
                    .opacity(1)
            }.frame(width: geometry.size.width, height: geometry.size.height, alignment: .center)
                .edgesIgnoringSafeArea(.all)
                .background(Color.black)
                .edgesIgnoringSafeArea(.all)
                .opacity(0.5)
        }
        
    }
}

struct GuestJiniAlerBox_Previews: PreviewProvider {
    static var previews: some View {
        GuestJiniAlerBox(showAlert: .constant(true),alertTitle: "Forgot Password",alertBody: "A link has been sent to your email account to reset your password.")
    }
}

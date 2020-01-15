//
//  AppAccessRequest.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 28/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct AppAccessRequest: View {
    @ObservedObject var viewRouter: ViewRouter
      @State var email:String = "";
    @State var mobileNumber:String = "";
    
    var body: some View {
        GeometryReader { geometry in
            VStack{
                HStack{
                    GuestJiniHeaderSmall()
                        .frame(width: geometry.size.width, height: geometry.size.height * 0.1, alignment: .center)
                        .background(Color("veryLightPink"))
                }
                HStack{
                    Button(action: {
                        // What to perform
                    }) { GuestJiniButtonSystemImagePlain(imageName: "arrow.left").padding(.leading,2)
                        
                    }
                    GuestJiniTitleText(title: "CUSTOMER CARE")
                    
                    Spacer()
                }.padding()
                VStack{
                    HStack{
                        
                        GuestJiniTitleText(title: "APP ACCESS REQUEST")
                        
                        Spacer()
                    }.padding()
                    HStack{
                        GuestJiniInformationText(information: "Experience like-minded co-living while you also perceive your passion and interests. ")
                    }.padding()
                    HStack{
                        GuestJiniErrorText(message: "Invalid Login Credentials")
                        
                    }.frame(width: geometry.size.width, height: 30, alignment: .center)
                    HStack{
                        GuestJiniFieldLabel(labelText: "REGISTERED EMAIL")
                        Spacer()
                    }
                    GuestJiniRegularTextBox(placeHolderText:  "email@ddress.com", text: self.$email)
                        .padding(.horizontal)
                    HStack{
                        GuestJiniFieldLabel(labelText: "REGISTERED MOBILE NUMBER")
                        Spacer()
                    }
                    GuestJiniRegularTextBox(placeHolderText: "Mobile number", text: self.$mobileNumber)
                        .padding(.horizontal)
                    
                    
                }
                
            }.frame(width: geometry.size.width, height: geometry.size.height, alignment: .top)
                .edgesIgnoringSafeArea(.all)
        }
    }
}

struct AppAccessRequest_Previews: PreviewProvider {
    static var previews: some View {
        AppAccessRequest(viewRouter: ViewRouter())
    }
}

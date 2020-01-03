//
//  LandingScreen.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 28/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct LandingScreen: View {
    @ObservedObject var viewRouter: ViewRouter
    var body: some View {
        GeometryReader { geometry in
            VStack{
                HStack{
                    CarouselImage()
                }
                .frame(width: geometry.size.width, height: geometry.size.height * 0.65, alignment: .top)
                    .edgesIgnoringSafeArea(.top)
                
                VStack{
                    GuestJiniLogoImage()
                    GuestJiniDescriptionText(description: "Lorem ipsum dolor sit amet, consectetur  adipiscing elit. Etiam erat sapien, ultricies. ")
                        .padding([.leading, .bottom, .trailing])
                    
                    Button(action: {
                        self.viewRouter.currentPage = ViewRoutes.LOGIN_PAGE
                    }) {
                        GuestJiniButtonSystemImage(imageName: "arrow.right")
                        
                    }
                }
            }.frame(width: geometry.size.width, height: geometry.size.height, alignment: .top)
            
        }
    }
}

struct LandingScreen_Previews: PreviewProvider {
    static var previews: some View {
        LandingScreen(viewRouter: ViewRouter())
    }
}

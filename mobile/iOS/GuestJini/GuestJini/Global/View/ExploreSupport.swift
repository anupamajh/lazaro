//
//  ExploreSupport.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 31/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct ExploreSupport: View {
    
    @ObservedObject var viewRouter: ViewRouter
    @State var searchText:String = ""
    
    var body: some View {
        GeometryReader { geometry in
            VStack{
                VStack{
                    HStack{
                        GuestJiniButtonSystemImagePlain(imageName: "arrow.left")
                        GuestJiniTitleText(title: "EXPLORE")
                        Spacer()
                    }.padding()
                    HStack{
                        GuestJiniSquareButtonSystemImage(systemImage: "slider.horizontal.3")
                        HStack{
                            VStack{
                                HStack{
                                    Text("86 articles found in 07 categories.")
                                        .font(Fonts.RobotSectionTitle)
                                    Spacer()
                                }
                                HStack{
                                    Text("Showing all articles")
                                        .font(Fonts.RobotRegularSmallText)
                                    Spacer()
                                }
                                
                            }
                            Spacer()
                        }
                    }.padding()
                    
                    GuestJiniSearchBox(text: self.$searchText).padding()
                    HStack{
                        Text("Showing 03 of 86")
                            .font(Fonts.RobotSectionTitle)
                        Spacer()
                    }.padding()
//                    ScrollView{
//                        VStack{
//                            
//                        }
//                    }
                }.frame(width: geometry.size.width, height: geometry.size.height-75, alignment: .top)
                    .padding()
                Divider()
                GuestJiniBottomBar(viewRouter: self.viewRouter)
            }.frame(width: geometry.size.width, height: geometry.size.height, alignment: .top)
                .edgesIgnoringSafeArea(.bottom)
        }
    }
}

struct ExploreSupport_Previews: PreviewProvider {
    static var previews: some View {
        ExploreSupport(viewRouter: ViewRouter())
    }
}

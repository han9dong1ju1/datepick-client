import SwiftUI

struct ContentView: View {
    
    @Environment(\.colorScheme) var colorScheme
    
    var body: some View {
        TabView {
            
            HomeView()
                .tabItem {
                    Image(systemName: "house.fill")
                    Text("Home")
                }
            
            MapView()
                .tabItem {
                    Image(systemName: "map.fill")
                    Text("Map")
                }
            
            MyDateView()
                .tabItem {
                    Image(systemName: "heart.fill")
                    Text("My Date")
                }
            
            ProfileView()
            
                .tabItem {
                    Image(systemName: "person.fill")
                    Text("Profile")
                }
            
        }.accentColor(colorScheme == .dark ? .white : .black)
        
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

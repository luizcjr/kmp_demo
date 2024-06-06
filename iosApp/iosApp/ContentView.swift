import SwiftUI
import shared

struct ContentView: View {
    @StateObject private var viewModel = HomeViewModel()
    
    var body: some View {
            VStack {
                switch viewModel.response {
                case let success as RequestState.Success:
                    List(success.data.items, id: \.id) { element in
                        ProductView(product: element)
                    }
                case let error as RequestState.Error:
                    VStack {
                        Spacer()
                        Text(error.message)
                            .fontWeight(.bold)
                        Spacer()
                    }
                    .frame(maxWidth: .infinity, maxHeight: .infinity)
                case _ as RequestState.Loading:
                    VStack {
                        Spacer()
                        ProgressView("Loading")
                            .progressViewStyle(CircularProgressViewStyle())
                            .padding()
                        Spacer()
                    }
                    .frame(maxWidth: .infinity, maxHeight: .infinity)
                case .none:
                    EmptyView()
                case .some(_):
                    EmptyView()
                }
            }
            .task {
                await viewModel.fetchData()
            }
        }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

class HomeViewModel: ObservableObject {
    @Published
    private(set) var response: RequestState? = nil
    let productLimit = 10
    
    @MainActor
    func fetchData() async {
        for await requestState in ProductsApi().fetchProducts(limit: Int32(productLimit)) {
            response = requestState
        }
    }
}

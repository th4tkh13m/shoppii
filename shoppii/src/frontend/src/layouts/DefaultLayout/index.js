import Navbar from '../Navbar'
import Footer from '../Footer'
import { Container } from 'react-bootstrap'
function DefaultLayout({ children }) {
    return (
        <div>
            <Navbar />
            <Container style={{ paddingTop: '5rem' }}>{children}</Container>
            {/* <Footer /> */}
        </div>
    )
}

export default DefaultLayout

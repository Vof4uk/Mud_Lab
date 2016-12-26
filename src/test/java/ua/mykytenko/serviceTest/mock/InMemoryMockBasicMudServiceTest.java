package ua.mykytenko.serviceTest.mock;

import org.springframework.test.context.ActiveProfiles;
import ua.mykytenko.Profiles;
import ua.mykytenko.serviceTest.BasicMudServiceTestingTest;

@ActiveProfiles(Profiles.IN_MEMORY_MAP)
public class InMemoryMockBasicMudServiceTest extends BasicMudServiceTestingTest{
}

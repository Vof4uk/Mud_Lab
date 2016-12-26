package ua.mykytenko.serviceTest.mock;

import org.springframework.test.context.ActiveProfiles;
import ua.mykytenko.Profiles;
import ua.mykytenko.serviceTest.SampleFamilyServiceTest;

@ActiveProfiles(Profiles.IN_MEMORY_MAP)
public class InMemoryMockFamilyServiceTest extends SampleFamilyServiceTest {
}

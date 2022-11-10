package com.example.countries.domain.use_case

import com.example.countries.common.Resource
import com.example.countries.data.remote.dto.country.CountriesDto
import com.example.countries.domain.repository.CountriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(
    private val repository: CountriesRepository ,
)
{
    operator fun invoke(): Flow<Resource<List<CountriesDto>>> = flow {
        try
        {
            emit(Resource.Loading())
            val countries = repository.getCountries()
            emit(Resource.Success(countries))
        } catch (e: HttpException)
        {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException)
        {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}